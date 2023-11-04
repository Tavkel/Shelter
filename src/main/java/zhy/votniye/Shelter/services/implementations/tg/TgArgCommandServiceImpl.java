package zhy.votniye.Shelter.services.implementations.tg;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.Report;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.services.interfaces.ReportService;
import zhy.votniye.Shelter.services.interfaces.tg.TgArgCommandService;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.utils.PhotoCompression;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class TgArgCommandServiceImpl implements TgArgCommandService {
    private Logger logger = LoggerFactory.getLogger(TgArgCommandServiceImpl.class);
    private final OwnerService ownerService;
    private final ReportService reportService;
    private final TgBotService botService;
    @Autowired
    private PhotoCompression compressor;
    private final TelegramBot telegramBot;

    public TgArgCommandServiceImpl(OwnerService ownerService, ReportService reportService, TgBotService botService, TelegramBot telegramBot) {
        this.ownerService = ownerService;
        this.reportService = reportService;
        this.botService = botService;
        this.telegramBot = telegramBot;
    }

    @Override
    public void submitReport(Message message) {
        try {
            var report = tryParseReport(message);
            reportService.createReport(report);
            var result = new SendMessage(message.chat().id(), "report accepted");
            telegramBot.execute(result);
        } catch (IllegalArgumentException e) {
            var result = new SendMessage(message.chat().id(), e.getMessage());
            telegramBot.execute(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Report tryParseReport(Message message) throws IOException {
        Pattern pattern = Pattern.compile(
                "(^/[\\w?]+)" +//command
                        "(\\s)" +//spacer
                        "([^*]+)" +//part1
                        "(\\*)" +//delimiter
                        "([^*]+)" +//part2
                        "(\\*)" +//delimiter
                        "(.+)"//part3
        );
        Matcher matcher = pattern.matcher(message.caption());
        if (message.photo() == null || message.photo().length == 0) {
            throw new IllegalArgumentException("no photo provided");
        }

        if (matcher.matches()) {
            var report = new Report();
            var owner = ownerService.getByChatId(message.chat().id())
                    .orElseThrow(() -> new NoSuchElementException("owner not found"));
            var monitor = owner.getReportMonitors().stream().filter(m -> m.isActive()).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("owner has no active monitors"));
            report.setOwner(owner);
            report.setApm(monitor);
            report.setDog(owner.getDogs().stream().filter(d -> d.getStatus() == Status.PetStatus.ON_PROBATION).findFirst()
                    .orElse(null));
            report.setCat(owner.getCats().stream().filter(c -> c.getStatus() == Status.PetStatus.ON_PROBATION).findFirst()
                    .orElse(null));
            report.setGeneralReport(matcher.group(3));
            report.setFeedingReport(matcher.group(5));
            report.setBehaviorReport(matcher.group(7));

            GetFile getFile = new GetFile(message.photo()[message.photo().length - 1].fileId());
            var response = telegramBot.execute(getFile);
            var url = telegramBot.getFullFilePath(response.file());
            Path filePath = Path.of("./reports/" + owner.getId() + "/" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString().replace(':','_') + ".jpg");
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);

            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                 BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {

                in.transferTo(bos);
                report.setPathToFile(filePath.toString());
                report.setPhoto(compressor.generatePreview(filePath));
            } catch (IOException e) {
                SendMessage errorMessage = new SendMessage(message.chat().id(), "encountered error while saving file");
                telegramBot.execute(errorMessage);
                throw new IllegalArgumentException("photo problem");
            }

            return report;
        } else {
            throw new IllegalArgumentException("wrong data provided");
        }
    }
}
