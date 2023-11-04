 -- liquibase formatted sql

 -- changeset tav:apm1
CREATE TABLE adoption_process_monitor(
id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
chat_id BIGINT NOT NULL,
start_date DATE NOT NULL,
end_date DATE NOT NULL,
latest_report TIMESTAMP WITH TIME ZONE,
is_active BOOLEAN NOT NULL,
CONSTRAINT apm_pk PRIMARY KEY (id),
CONSTRAINT "FK_apm_owner" FOREIGN KEY (chat_id) REFERENCES owner(telegram_chat_id)
);