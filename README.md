# Expression Resolver

[![CircleCI](https://circleci.com/gh/malkaviano/expressionresolver.svg?style=svg)](https://circleci.com/gh/malkaviano/expressionresolver)

It was originally created for custom querying a collection 

Static type check and customizable filtering for collections, based on json input filter your collections with on the fly created queries.

The UI for generating the JSON is not included in the project and is out of the project scope, you may read the unit tests to understand how to use it

It accepts: String, Int, Double and Date (Joda)

Example:

Given a case class





```  
case class Something(
                        name: String,
                        birth: Option[DateTime],
                        commendations: Int
                      )
```

And a collection

```
Seq(
        Something("Rafael", Option(DateTime.parse("1980-02-15")), 10),
        Something("Thiago", None, 0),
        Something("Camilla", Option(DateTime.parse("1900-12-30")), 5),
        Something("Juliana", None, 50),
      )
```

The following json will filter only Camilla record

```
{
 "oper" : "and",
 "values" : [
   {
     "oper" : "less", "values" : [
       {"oper" : "prop", "values" : [ "commendations" ], "tag" : "number" },
       {"oper" : "literal", "values" : [ 10 ], "tag" : "number" }
     ]
   },
   {
     "oper" : "not", "values" : [
       { "oper" : "eq", "values" : [
           {"oper" : "literal", "values" : [ "Thiago" ], "tag" : "text" },
           {"oper" : "prop", "values" : [ "name" ], "tag" : "text" }
         ]
       }
     ]
   }
 ]
}
```

This is a POC, it's not intended to be used in production as it is