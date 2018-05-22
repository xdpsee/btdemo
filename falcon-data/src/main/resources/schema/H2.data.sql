insert into models (`name`,
                    protocol,
                    supported_commands,
                    attributes
) values (
  'MOBILE',
  'mobile',
  '["TYPE_CUSTOM"]',
  '{}'
);

insert into devices (
  id,
  protocol,
  model,
  gmt_create,
  gmt_update,
  attributes
) values (
  'IMEI-880113402022080',
  'MOBILE',
  'mobile',
  '2018-05-22 21:00:00',
  '2018-05-22 21:00:00',
  '{}'
);
