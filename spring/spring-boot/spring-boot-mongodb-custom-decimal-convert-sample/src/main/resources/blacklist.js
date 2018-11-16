var blacklist1 = {
  "username": "snowwolf",
  "userid": "2018001014344",
  "address": {
    "aCode": "0020",
    "add": "广州"
  },
  "certificate": {
    "certificateid": "20018554111134",
    "certificatetype": "01",
    "desc": "学生证"
  },
  "blacktype": "01",
  "comments": {
    "cause": "01",
    "desc": "逾期欠费",
    "money": NumberDecimal("18889.09")
  },
  "status": "01",
  "update": ISODate("2017-12-06T04:26:18.354Z"),
  "indate": ISODate("2017-12-06T04:26:18.354Z")
};

var blacklist2 = {
  "username": "lison",
  "userid": "2018001014345",
  "address": {
    "aCode": "0075",
    "add": "深圳"
  },
  "certificate": {
    "certificateid": "20018554111134",
    "certificatetype": "02",
    "desc": "护照"
  },
  "blacktype": "01",
  "comments": {
    "cause": "02",
    "desc": "恶意欠费",
    "money": NumberDecimal("188890.00")
  },
  "status": "01",
  "update": ISODate("2016-01-06T04:26:18.354Z"),
  "indate": ISODate("2015-12-06T04:26:18.354Z")

};


var blacklist3 = {
  "username": "tom",
  "userid": "2018001014346",
  "address": {
    "aCode": "0020",
    "add": "广州"
  },
  "certificate": {
    "certificateid": "20018554111136",
    "certificatetype": "01",
    "desc": "学生证"
  },
  "blacktype": "01",
  "comments": {
    "cause": "03",
    "desc": "公安机关确定的涉嫌短信欺诈、诈骗等犯罪行为的用户",
  },
  "status": "01",
  "update": ISODate("2017-12-06T04:26:18.354Z"),
  "indate": ISODate("2017-12-06T04:26:18.354Z")

};

db.blacklist.insert(blacklist1);
db.blacklist.insert(blacklist2);
db.blacklist.insert(blacklist3);

