INSERT INTO T_HOUSEHOLD_MEMBER (household_id, user_id, role, archived, created_at, created_by)
VALUES
    (1, 1, 'OWNER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (1, 2, 'MEMBER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (2, 3, 'OWNER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (2, 4, 'MEMBER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (2, 7, 'MEMBER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (3, 5, 'MEMBER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (3, 6, 'OWNER', false, CURRENT_TIMESTAMP, 'patrick@test.com'),
    (4, 5, 'OWNER', false, CURRENT_TIMESTAMP, 'patrick@test.com')
;
