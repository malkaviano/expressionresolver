# Expression Resolver for custom collection searchs

Static type check and customizable filtering for collections, based on json input filter your collections with on the fly created queries.

The UI for generating the JSON is not included in the project and is out of the project scope, you may read the unit tests to understand how to use it

It accepts: String, Int, Double and Date (Joda)

Example:

`
{
   "oper" : "and",
   "values" : [
     {
       "oper" : "less",
       "values" : [
         { "oper" : "literal", "values": [ 4 ], "tag" : "number" },
         { "oper" : "prop", "values": [ "age" ], "tag" : "number" }
       ]
     },
     {
       "oper" : "not",
       "values" : [
         {
           "oper" : "eq",
           "values" : [
             { "oper" : "literal", "values": [ "1990-01-01" ], "tag" : "date" },
             { "oper" : "prop", "values": [ "birth" ], "tag" : "date" }
           ]
         }
       ]
     }
   ]
}
`

This is a POC, it's not intended to be used in production as it is

TODO:

=> Better design

=> Reflection may be insecure without whitelist/blacklist

=> Accept Function and Map as arguments to be filtered
