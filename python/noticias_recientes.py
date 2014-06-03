import urllib
import urllib2
import time
import datetime
import json
from pprint import pprint

"""
API Publica educ.ar

Ejemplo de llamado a endpoint: Noticias Recientes
 
"""

url = 'https://api.educ.ar/0.9/noticias/recientes/'
params = {'key' : 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'}

req = urllib2.Request(url, urllib.urlencode(params))

resp = urllib2.urlopen(req).read()

data = json.loads(resp)
pprint(data)
