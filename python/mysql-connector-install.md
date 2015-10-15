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

import mysql.connector

# Open database connection
conn = mysql.connector.connect(host="<address>", user="<account>", password="<password>", database="testdb")

# prepare a cursor object using cursor() method
cursor = db.cursor()

# execute SQL query using execute() method.
cursor.execute("SELECT VERSION()")

# Fetch a single row using fetchone() method.
data = cursor.fetchone()

print "Database version : %s " % data

# Close cursor
cursor.close()

# disconnect from server
db.close()
```

## Reference
* https://dev.mysql.com/doc/connector-python/en/
