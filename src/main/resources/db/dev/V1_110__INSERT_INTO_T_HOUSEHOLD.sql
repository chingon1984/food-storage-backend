INSERT INTO T_HOUSEHOLD (name, public_id, description, archived, created_at, created_by)
VALUES
    ('Familie Hasselbach', gen_random_uuid(), 'Haushalt der Familie Hasselbach', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    ('WG Reim', gen_random_uuid(), 'WG von Laurin Reim', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    ('WG Hohenzollernstr', gen_random_uuid(), 'WG von Robert Götz', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    ('Familie Götz', gen_random_uuid(), 'Haushalt der Familie Götz', false, CURRENT_TIMESTAMP, 'patrick@test.com')
    ;
