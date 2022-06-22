CREATE SEQUENCE history_id_seq;

CREATE TABLE history (
    id             oid CONSTRAINT history_primary_key PRIMARY KEY DEFAULT nextval('history_id_seq'),
    occurred_at    timestamp,
    username       name,
    context        name,
    action         name,
    successful     bool,
    log            text
    --command_args   text,
    --command_result text
);
