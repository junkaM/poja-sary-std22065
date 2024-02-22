create table if not exists operation_sary (
    id varchar primary key,
    operation_timestamp timestamp not null default (strftime('%Y-%m-%d %H:%M:%S', 'now'))
);