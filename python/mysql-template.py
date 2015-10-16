#!/usr/bin/env python
# -*- coding: utf-8 -*-

import mysql.connector

class ResultCallback():
    def handle(self, cursor):
        raise NotImplementedError()

def execute_query(db_name, query, callback):
    dbconfig = get_dbconfig(db_name)
    conn = mysql.connector.connect(**dbconfig)

    cursor = conn.cursor()
    cursor.execute(query)

    if callback != None:
        callback.handle(cursor)
    else:
        # throw out remaining results
        cursor.fetchall()

    cursor.close()
    conn.close()

def get_dbconfig(db_name):
    # FIXME:
    return {
            "database": db_name,
            "host": "localhost",
            "user": "root",
            "password": "password",
    }

if __name__ == "__main__":
    # test code
    class UserResultCallback(ResultCallback):
        def handle(self, cursor):
            self.result = cursor.fetchall()

    query = "select * from user"
    db_name = "mysql"
    callback = UserResultCallback()
    execute_query(db_name, query, callback)

    print "result: %s" % callback.result

