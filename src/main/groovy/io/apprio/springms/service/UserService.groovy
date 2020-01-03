package io.apprio.springms.service

import io.apprio.springms.api.model.UserProfile
import io.apprio.springms.domain.User
import io.apprio.springms.repositrory.CrudRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    
    @Autowired
    CrudRepository crudRepository
    
    UserProfile create(UserProfile userProfile) {
        crudRepository.create(new User(fNamne: userProfile.firstName))
        return userProfile
    }
}
