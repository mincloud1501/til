#!/usr/bin/env python
# -*- coding: utf-8 -*-

import mysql.connector
from contextlib import * 

class ResultCallback():
    def handle(self, cursor):
        raise NotImplementedError()

def execute_query(query, args=(), cb=None, conn = None, dbconfig = None):
    if conn != None:
        execute(query, args, cb, conn)
    elif dbconfig != None:
        execute_atomic(query, args, cb, dbconfig)
    else:
        raise RuntimeError('At least one of conn or dbconfig should be specified')

def execute(query, args, cb, conn):
    __execute_query(query, args, cb, conn)

def execute_atomic(query, args, cb, dbconfig):
    with closing(mysql.connector.connect(**dbconfig)) as conn:
        conn.autocommit = True
        __execute_query(query, args, cb, conn)

def __execute_query(query, args, cb, conn):
    with closing(conn.cursor()) as cursor:
        cursor.execute(query, args)

        if cb != None:
            cb.handle(cursor)
        else:
            cursor.fetchall()

@contextmanager
def transaction(dbconfig):
    conn = mysql.connector.connect(**dbconfig)
    _org = conn.autocommit

    conn.autocommit = False

    try:
        yield conn
        conn.commit()
    except:
        conn.rollback()
    finally:
        conn.autocommit = _org
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
    callback = UserResultCallback()
    execute_query(query, cb=callback, dbconfig=get_dbconfig("mysql"))

    print "result: %s" % callback.result

