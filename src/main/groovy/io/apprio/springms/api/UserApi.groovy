package io.apprio.springms.api

import io.apprio.springms.api.model.UserProfile

interface UserApi {

    UserProfile create(UserProfile userProfile)
    UserProfile update(UserProfile userProfile)
    UserProfile get(String id)
    UserProfile findById(String id)
    List<UserProfile> findBy(UserProfile userProfile)
}