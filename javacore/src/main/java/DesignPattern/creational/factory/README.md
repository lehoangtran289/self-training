
## 1. Simple Factory Pattern

Creates objects without exposing the instantiation logic to the client.
> Client just need a class and don't bother about implementer

```java
SimpleFactory simpleFactory = new SimpleFactory();
IAnimal dog = simpleFactory.CreateDog(); // Create dog
IAnimal tiger = simpleFactory.CreateTiger(); // Create tiger
```
---

## 2. Factory Method Pattern
Defines an interface for creating objects, but let subclasses to decide which class to instantiate.
> Client doesn't know what concrete classes it will be required to create the object

```java
AnimalFactory dogFactory = new DogFactory();
IAnimal dog = dogFactory.CreateAnimal(); // Create dog

AnimalFactory tigerFactory = new TigerFactory();
IAnimal tiger = tigerFactory.CreateAnimal(); // Create tiger
```

---

## 3. Abstract Factory pattern (factory of factories)

Abstract Factory offers the interface for creating a family of related objects, without explicitly specifying their classes
> When your system has to create multiple families of products without exposing the implementation details.
> 
```java
IAnimalFactory petAnimalFactory = FactoryProvider.GetAnimalFactory("pet");
IDog dog = petAnimalFactory.GetDog(); // Create pet dog
ITiger tiger = petAnimalFactory.GetTiger();  // Create pet tiger

IAnimalFactory wildAnimalFactory = FactoryProvider.GetAnimalFactory("wild");
IDog dog = wildAnimalFactory .GetDog(); // Create wild dog
ITiger tiger = wildAnimalFactory .GetTiger();  // Create wild tiger

```
