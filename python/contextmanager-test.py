from contextlib import contextmanager

class mysql_connector_connect():
    def __init__(self,host,user,password,table):
        print "mysql connector opened"

        self.host = host
        self.user = user
        self.password = password
        self.table = table

    def execute(self,query):
        print "excute query> %s" % query

    def close(self):
        print "mysql connector closed"

@contextmanager
def mysql_open(host,user,password,table):
    conn = mysql_connector_connect(**locals())
    try:
        yield conn
    finally:
        conn.close()

if "__main__" == __name__:
    with mysql_open('localhost','root','password','database') as conn:
        conn.execute("select * from table")

