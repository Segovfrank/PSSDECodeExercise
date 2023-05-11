PS SDE Code Exercise
==================

**Building & Running**

- You can just clone this repo.
- App is using latest AGP, so JDK 17 is required.
- Use Android Studio Embedded JDK for a quick setup. Go to: File -> Settings -> Build, Execution,
  Deployment -> Build Tools -> Gradle -> Gradle JDK (Embedded JDK 17.0.6).

**App Design**

- Decided to use a simple MVVM architecture with 1 main activity and 2 fragments.
- MVVM uses a stateflow to communicate the UI state with the Fragment List, when the input list is
  loaded this will be
  updated on the UI.

When clicking on an element on list, it will take you to a detail view after calculating the SS for
that driver where you will see:

- Driver Name
- Shipment Address
- Suitability Score

**Libraries Used**

- Fragments
- ViewModel
- RecyclerView
- Gson
- Navigation
- Lottie (animations)