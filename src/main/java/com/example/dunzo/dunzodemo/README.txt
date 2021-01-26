The work in this project was done in a span of ~1-2 hours of time. The abstractions I came up with favour simplicity and maintainability but there is a lot of scope for improvement in my opinion.

ASSUMPTIONS:

1. If there is no free port(/outlet) then the machine rejects the order immediately.
2. Currently to simulate work being done, there is sleep period of 5 seconds.
3. If any ingredient is not sufficient to complete order, the order is rejected.
4. To get low quantity ingredients in inventory, there is public method in CoffeeMachine class.
5. To refill the machine, there is a refill method in CoffeeMachine class.
6. I have ignored replacing String names for ingredients with enums and other such minor improvements to favour simplicity and to save time :D.

