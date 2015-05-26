#include "test.h"
#include "users/password.h"
#include <regex>
/** Regular Expression to match the Salt, which is a UUID */
static const auto SALT_REGEX = std::regex("^[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}$");

/** Regular Expression to match the Hash, which is an SHA256 hash as hex pairs  */
static const auto HASH_REGEX = std::regex("^[a-z0-9]{64}$");

const lest::test module[] = {
  SCENARIO("Hashing a password") {
    GIVEN("A Plaintext password") {
      const std::string plaintext = "password";
      
      WHEN("Hashed with a random salt") {
        const Users::Password password = Users::Password::hash(plaintext);
        
        THEN("The salt is a UUID") {
          EXPECT(std::regex_match(password.salt(), SALT_REGEX));
        }
        
        THEN("The hash is an SHA256 string") {
          EXPECT(std::regex_match(password.hash(), HASH_REGEX));
        }
      }  
    }
  }
};

MODULE(specification(), module);
