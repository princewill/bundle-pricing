## Bundle Pricing  

This exercise is a common problem in e-commerce and brick-and- mortar retail systems.

A customer shops from some form of catalog, selecting items and quantities they wish to

purchase. When they are ready, they “check out”, that is, complete their purchase, at which

point they are given a total amount of money they owe for the purchase of a specific set of

items. In the bounds of this problem, certain groups of items can be taken together as a

“bundle” with a different price. E.g. if I buy single apple in isolation it costs $1.99, if I buy two

apples it’s $2.15. More complex combinations are possible – e.g. a loaf of bread “A” purchased

with two sticks of margarine “B” and the second stick of margarine is free (e.g. $0).

The same item may appear in more than one bundle, e.g. any one “cart” of items might be able

to be combined in more than one way. For this exercise, produce an API and implementation

for a service that accepts a collection of items the customer wishes to purchase (e.g. items and

quantities), and produces the lowest possible price for that collection of items. The API is to be

called by other applications in the same JVM, e.g. don’t worry about providing a REST or other

remote interface to the API, just the actual method calls is fine. The API is initialized with

information that provides all possible bundles and the catalog of items and their prices. Once it

is initialized, many calls can be made at once to the API to produce a total price for collections

of items, and it should be able to handle multiple simultaneous calls without errors (e.g. if I’m

computing the price for one call, that should not interfere with computing the price for another

call). Use what you would consider good Scala style.

We are not expecting conformance to any specific coding standard for this exercise, although

you should be consistence throughout the exercise.

Please return the source code to your solution and any additional code you think is required to

demonstrate its correctness under the specification provided. You can provide the code via any

convenient means, e.g. zip file, link to a readable code repository, etc.

## Instructions

Step 1: Extract Zip

Step 2: SBT

Step 3: SBT Compile

Step 4: SBT Test


## NOTE:

- The API is initialized with information that provides all possible bundles and the catalog of items and their prices.

- Entries can be entered multiple times to signify quantity.

- Used Joda Lib to integrate prices using CAD currency unit. 

- Only bundles that were successfully applied to the original order are used when doing recursion into combinations

- Data transfer object (DTO) is used to reduce to number of calls because majority of the cost of each call is related to the round-trip time.