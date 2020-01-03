package io.apprio.springms.repositrory.impl


import io.apprio.springms.domain.User
import io.apprio.springms.repositrory.CrudRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class UserRepository implements CrudRepository<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepository);

    @Override
    User create(User user) {
        Thread.sleep(5000)
        //throw new RuntimeException('Couldn\'t create user')
        return new User(id: UUID.randomUUID().toString())
    }

    @Override
    User read(User user) {
        return new User(id: UUID.randomUUID().toString())
    }

    @Override
    User update(User user) {
        return null
    }

    @Override
    User delete(User user) {
        return null
    }
}
