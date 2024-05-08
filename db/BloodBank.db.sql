BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Personal" (
	"id"	INTEGER UNIQUE,
	"name"	TEXT,
	"surname"	TEXT,
	"phone"	TEXT UNIQUE,
	"hours"	INTEGER,
	"work_type"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Hospital" (
	"id"	INTEGER UNIQUE,
	"name"	TEXT,
	"address"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Request" (
	"id"	INTEGER UNIQUE,
	"hospital_id"	INTEGER,
	"blood_id"	INTEGER,
	"date"	DATE,
	"status"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("hospital_id") REFERENCES "Hospital"("id"),
	FOREIGN KEY("blood_id") REFERENCES "Blood"("id")
);
CREATE TABLE IF NOT EXISTS "Donor" (
	"id"	INTEGER UNIQUE,
	"name"	TEXT,
	"surnam"	TEXT,
	"blood_type"	INTEGER,
	"date"	DATE,
	"times_donated"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Blood" (
	"id"	INTEGER UNIQUE,
	"date"	DATE,
	"liters"	NUMERIC,
	"personal_id"	INTEGER,
	"donor_id"	INTEGER,
	PRIMARY KEY("id" AUTOINCREMENT),
	FOREIGN KEY("personal_id") REFERENCES "Personal"("id"),
	FOREIGN KEY("donor_id") REFERENCES "Donor"("id")
);
COMMIT;
