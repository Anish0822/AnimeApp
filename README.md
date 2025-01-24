# AnimeApp
# Feature & Technology Used
- This Android app follows the MVVM (Model-View-ViewModel) architecture, ensuring a clean and maintainable codebase. It leverages LiveData for observing changes in the data and updating the UI accordingly.
- Glide is used to efficiently load and display images, such as anime posters, ensuring smooth and quick image rendering.
- The app makes network requests using Retrofit, and OkHttp Interceptors are used for logging network requests and responses to assist with debugging and monitoring.
- For the user interface, RecyclerView is utilized to display a list of anime, and WebView is used to show trailers when available.
- A ProgressBar is implemented to provide feedback during data loading.
- The app fetches data from the Jikan API, using endpoints for top-rated anime and anime details, including the title, rating, genres, and episodes.
