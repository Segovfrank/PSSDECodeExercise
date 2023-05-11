PS SDE Code Exercise
==================

**App Design**
- Decided to use a simple MVVM architecture with 1 main activity and 2 fragments.
- MVVM uses a stateflow to communicate the UI state with the Fragment List, when the input list is loaded this will be
updated on the UI.

When clicking on an element on list, it will take you to a detail view for that driver where you will see:
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