INSERT INTO T_STORAGE(name, public_id, household_id, description, archived)
VALUES
    ('Storage_living-room', gen_random_uuid(), 1, 'Storage at the living room', false),
    ('Storage_garage', gen_random_uuid(), 1, 'Storage at the garage', false),
    ('Storage_basement', gen_random_uuid(), 1, 'Storage at the basement', false),
    ('Storage_sleeping-room', gen_random_uuid(), 2, 'Storage at the sleeping room', false),
    ('Storage_secret-room', gen_random_uuid(), 2, 'Storage at the secret room', false),
    ('Storage_garden', gen_random_uuid(), 2, 'Storage at the garden', true),
    ('Storage_roof', gen_random_uuid(), 1, 'Storage at the roof', true);

