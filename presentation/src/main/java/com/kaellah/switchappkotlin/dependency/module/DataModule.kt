package com.kaellah.switchappkotlin.dependency.module

import com.kaellah.data.api.MoviesService
import com.kaellah.data.repository.movie.MovieDataRepository
import com.kaellah.domain.repository.movie.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {


    //    @Singleton
//    @Provides
//    fun provideSignUpModel(context: Context, gson: Gson, authService: AuthService, preferences: SharedPreferences, dropChatsUseCase: DropChatsUseCase)
//            : AuthModel = AuthDataModel(context, gson, authService, preferences, dropChatsUseCase)
//
//    @Singleton
//    @Provides
//    fun provideGalleryModel(context: Context): GalleryModel = GalleryDataModel(context)
//
    @Singleton
    @Provides
    fun provideMoviesRepo(apiService: MoviesService): MovieRepository = MovieDataRepository(apiService)
//
//    @Singleton
//    @Provides
//    fun provideMediaModel(context: Context): MediaModel = MediaDataModel(context)
//
//    @Singleton
//    @Provides
//    fun provideUploadModel(uploadService: UploadService): UploadModel = UploadDataModel(uploadService)
//
//    @Singleton
//    @Provides
//    fun provideChatRepo(client: OkHttpClient, gson: Gson, chatService: ChatService, appDatabase: AppDatabase, @AuthToken token: Provider<String>, @UserId userId: Provider<String>): ChatRepository
//            = ChatDataRepository(client, gson, chatService, appDatabase, userId, token)
//
//    @Singleton
//    @Provides
//    fun provideSocialDataRepo(gson: Gson, apiService: SocialService, userService: UserService): SocialRepository = SocialDataRepository(gson, apiService, userService)
//
//    @Singleton
//    @Provides
//    fun provideWalletRepo(context: Context, apiService: WalletService): WalletRepository = WalletDataRepository(context, apiService)
//
//    @Singleton
//    @Provides
//    fun provideUserDataRepo(userService: UserService, appDatabase: AppDatabase): UserRepository
//            = UserDataRepository(userService, appDatabase.userDao())
//
//    @Singleton
//    @Provides
//    fun provideDiscoverRepository(context: Context, gson: Gson): DiscoverRepository = DiscoverDataRepository(context, gson)
}
