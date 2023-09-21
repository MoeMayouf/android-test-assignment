# android-test-assignment
Template project for Shackle Android interview assignment

### Tech stack & Open-source libraries
- Written in [Kotlin](https://kotlinlang.org/)
- Implementing MVVM design pattern with Android Architecture Components by following clean architecture principles.
- Dependency injection with [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- Observing data changes and updating the UI state with [StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/)
- [Room Database](https://developer.android.com/training/data-storage/room)- Constructing SQLite database more easily
- [Jetpack](https://developer.android.com/jetpack) libraries
    - [Navigation](https://developer.android.com/guide/navigation) - Handling navigation between destinations within the app
    - [Jetpack Compose](https://developer.android.com/jetpack/compose)
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Handling lifecycles with lifecycle-aware component
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Storing and managing UI-related data in a lifecycle-conscious way
    - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Injecting dependencies

The project is structured into several modules and they are as follows;
- app
- data
- database
- di
- domain
- network

Multi-modularisation to allow better separation of concerns and scalability, here for more info

Potential Improvements:
- More Unit testing
- Inegrate with Jacoco for better coverage
- Implement snapshot testing
- Further breakdown the Compose elements for resuablity
- Implement feature module to allow seperation of features
- Error handling
- Use of deteket and lint
