DROP TABLE IF EXISTS `workouts`;

CREATE TABLE workouts(
workout_id INT UNSIGNED NOT NULL PRIMARY KEY,
weekDay VARCHAR(30)DEFAULT NULL,
workoutCSV VARCHAR (200) DEFAULT NULL
);

INSERT INTO workouts VALUES (1, 'Monday', '');
INSERT INTO workouts VALUES (2, 'Tuesday',    '');
INSERT INTO workouts VALUES (3, 'Wednesday',  '');
INSERT INTO workouts VALUES (4, 'Thursday',   '');
INSERT INTO workouts VALUES (5, 'Friday',    '');
INSERT INTO workouts VALUES (6, 'Saturday',    '');
INSERT INTO workouts VALUES (7, 'Sunday',    '');