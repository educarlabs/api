import urllib
import urllib2
import time
import datetime
import json
from pprint import pprint

"""
API Publica educ.ar

Ejemplo de llamado a endpoint: Busqueda de Noticias
 
"""

url = 'https://api.educ.ar/0.9/noticias/articulos/'
params = {'key' : 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx',
          'texto' : 'hackaton',
          'limit' : '1',
          'sort_mode' : 'asc'}

req = urllib2.Request(url, urllib.urlencode(params))

resp = urllib2.urlopen(req).read()

data = json.loads(resp)
pprint(data)
