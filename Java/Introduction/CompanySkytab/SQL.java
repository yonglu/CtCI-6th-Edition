package CompanySkytab;

import java.util.NoSuchElementException;

public class SQL {

/*
	QUESTION #3: Relational databases & SQL

	Assume there are two database tables, `dinglebop` and `fleeb`.  `dinglebop` has
	a single primary key column `dinglebop_id`, and `fleeb` has a single primary
	key column `fleeb_id`.
	There's also an associative table `grumbo (dinglebop_id, fleeb_id)` which references `dinglebop` and `fleeb` with foreign keys.


	1. `grumbo` establishes a many-to-many relationship between `dinglebop` and `fleeb`. 
	What needs to happen to ensure that  `dinglebop` can be associated to same `fleeb` only once?


	2. Write an SQL query displaying counts of each `fleeb` grouped by the
	associated `dinglebop`. Include the `dinglebop` records that have no `fleeb`,
	example output:

	  +--------------+-------------+
	  | dinglebop_id | fleeb_count |
	  +--------------+-------------+
	  |            1 |           3 |
	  |            2 |           1 |
	  |            3 |           0 | # 0 or NULL
	  +--------------+-------------+



	3. Write an SQL query to find all `fleeb` that are not associated with any `dinglebop`

	---

	ANSWER #3:

	1. 'grumbo' has 'dinglebop_id' and 'fleeb_id' columns as foreign keys to the 'dinglebop' and 'fleeb'
	   table.  Also, it uses a combination of these two columns as the primary key.  That way it ensures 
	   'dinglebop' can be associated to same 'fleeb' only once.
	   
	2. SELECT dinglebop.dinglop_id, count(fleeb.fleeb_id) FROM dinglebop
	   LEFT JOIN grumbo ON dinglebop.dinglebop_id = grumbo.dinglebop_id
	   LEFT JOIN fleeb ON grumbo.fleeb_id = fleeb.fleeb_id
	   GROUP BY dinglebop.dinglebop_id
	   
	3. SELECT fleeb.fleeb_id, dinglebop_id from fleeb
	   LEFT JOIN grumbo ON grumbo.fleeb_id = fleeb.fleed_id
	   WHERE dinglebop_id IS NULL

tested in http://sqlfiddle.com:

CREATE TABLE dinglebop (
    dinglebop_id varchar(50) PRIMARY KEY,
    dinglebop_name varchar(500) NOT NULL
);

INSERT INTO dinglebop (dinglebop_id, dinglebop_name) VALUES
  ('1', 'John Green'),
  ('2', 'John Red'),
  ('3', 'John Blue'),
  ('4', 'John White');

CREATE TABLE fleeb (
    fleeb_id varchar(50) PRIMARY KEY,
    fleeb_name varchar(50) NOT NULL
);

INSERT INTO fleeb (fleeb_id, fleeb_name) VALUES
  ('101', 'The earth is flat'),
  ('102', 'One hundred angels can dance on the head of a pin'),
  ('103', 'The earth is flat and rests on a bull horn'),
  ('104', 'The earth is like a ball.');
  
-- This is the junction table.
CREATE TABLE grumbo (
    dinglebop_id varchar(50) REFERENCES dinglebop (dinglebop_id),
    fleeb_id varchar(50) REFERENCES fleeb (fleeb_id),
    PRIMARY KEY (dinglebop_id , fleeb_id)
);

INSERT INTO grumbo (dinglebop_id, fleeb_id) VALUES
  ('1', '101'),
  ('1', '102'),
  ('2', '103'),
  ('3', '104');

SELECT dinglebop.dinglebop_id, count(fleeb_name) as book_count
FROM dinglebop
LEFT JOIN grumbo ON dinglebop.dinglebop_id = grumbo.dinglebop_id
LEFT JOIN fleeb ON fleeb.fleeb_id = grumbo.fleeb_id
GROUP BY dinglebop.dinglebop_id

SELECT fleeb.fleeb_id, dinglebop_id FROM fleeb
left join grumbo on grumbo.fleeb_id = fleeb.fleeb_id
Where dinglebop_id IS NULL

*/
	
	public static void main(String[] args) {
		System.out.println("SQL");
	}
	

}

