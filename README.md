# project_myroom

## wireFrame
https://www.figma.com/file/HZoYXenPrxhcRxYI4MYe3L/MyRoom?node-id=0%3A1&t=ghbLlSwI6uE4aODV-1

## decomposition diagram

## dependency diagram
https://drive.google.com/file/d/1fnI5YEC05caYCsK0N_eD3K8wonXKtq7I/view?usp=sharing

## Data Crawling
```{py}
def getRealEstateId(geohash):
    url = "https://apis.zigbang.com/v2/items?domain=zigbang&geohash={}".format(geohash)
    req = requests.get(url)
    items = req.json()
    
    idList=[]
    for i in items["items"]:
        idList.append(i["item_id"])
        
    return idList


idList = getRealEstateId(geohash)
```
