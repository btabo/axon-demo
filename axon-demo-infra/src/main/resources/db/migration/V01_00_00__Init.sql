-- Event Store
create sequence hibernate_sequence start 1 increment 1;
create table association_value_entry (id int8 not null, association_key varchar(255) not null, association_value varchar(255), saga_id varchar(255) not null, saga_type varchar(255), primary key (id));
create table domain_event_entry (global_index int8 not null, event_identifier varchar(255) not null, meta_data oid, payload oid not null, payload_revision varchar(255), payload_type varchar(255) not null, time_stamp varchar(255) not null, aggregate_identifier varchar(255) not null, sequence_number int8 not null, type varchar(255), primary key (global_index));
create table saga_entry (saga_id varchar(255) not null, revision varchar(255), saga_type varchar(255), serialized_saga oid, primary key (saga_id));
create table snapshot_event_entry (aggregate_identifier varchar(255) not null, sequence_number int8 not null, type varchar(255) not null, event_identifier varchar(255) not null, meta_data oid, payload oid not null, payload_revision varchar(255), payload_type varchar(255) not null, time_stamp varchar(255) not null, primary key (aggregate_identifier, sequence_number, type));
create table token_entry (processor_name varchar(255) not null, segment int4 not null, owner varchar(255), timestamp varchar(255) not null, token oid, token_type varchar(255), primary key (processor_name, segment));
create index IDXk45eqnxkgd8hpdn6xixn8sgft on association_value_entry (saga_type, association_key, association_value);
create index IDXgv5k1v2mh6frxuy5c0hgbau94 on association_value_entry (saga_id, saga_type);
alter table domain_event_entry add constraint UK8s1f994p4la2ipb13me2xqm1w unique (aggregate_identifier, sequence_number);
alter table domain_event_entry add constraint UK_fwe6lsa8bfo6hyas6ud3m8c7x unique (event_identifier);
alter table snapshot_event_entry add constraint UK_e1uucjseo68gopmnd0vgdl44h unique (event_identifier);

-- PLR Query Model
create table plr (plr_id varchar(255) not null, loan_amount numeric(19, 2), loan_term int4, revenues numeric(19, 2), primary key (plr_id));
create table plr_profiles (plr_plr_id varchar(255) not null, profiles_id varchar(255) not null, primary key (plr_plr_id, profiles_id));
create table profile (id varchar(255) not null, customer_id varchar(255), insurance_formula varchar(255), insurance_type varchar(255), plr_plr_id varchar(255), primary key (id));
alter table plr_profiles add constraint UK_ifcojgonroj4thd5dqf72onxc unique (profiles_id);
alter table plr_profiles add constraint FKr7hfx62amdrwh40cl2tmojvn9 foreign key (profiles_id) references profile;
alter table plr_profiles add constraint FKbwqn256ssjvmq3qe3biowo347 foreign key (plr_plr_id) references plr;
alter table profile add constraint FK8qbvkj2d8f7pk4uemt9rf1bfd foreign key (plr_plr_id) references plr;