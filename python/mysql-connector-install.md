Python mysql connector 설치
=====

## Prerequisite
* MySQL Connector for C (only for windows)
  * http://dev.mysql.com/get/Downloads/Connector-C/mysql-connector-c-6.0.2-winx64.msi

## Installation

```bash
$ easy_install mysql-connector-python
```

## Test

```python
#!/usr/bin/python

import MySQLdb

# Open database connection
db = MySQLdb.connect("<address>","<account>","<password>","testdb" )

# prepare a cursor object using cursor() method
cursor = db.cursor()

# execute SQL query using execute() method.
cursor.execute("SELECT VERSION()")

# Fetch a single row using fetchone() method.
data = cursor.fetchone()

print "Database version : %s " % data

# disconnect from server
db.close()
```

