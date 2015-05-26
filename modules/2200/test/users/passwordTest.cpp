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
  },

  SCENARIO("Comparing Passwords") {
    GIVEN("A hashed password") {
      const Users::Password password = Users::Password::hash("password", "salt");
      
      WHEN("Comparing to another hashed password") {
        THEN("The same password is a match") {
          EXPECT(Users::Password::hash("password", "salt") == password);
        }
        
        THEN("A different salt is not a match") {
          EXPECT_NOT(Users::Password::hash("password", "different") == password);
        }
        
        THEN("A different password is not a match") {
          EXPECT_NOT(Users::Password::hash("different", "salt") == password);
        }
      }
      
      WHEN("Comparing to a string") {
        THEN("The same password is a match") {
          EXPECT(password == "password");
        }
        THEN("A different password is a match") {
          EXPECT_NOT(password == "different");
        }
      }
      
      WHEN("Comparing a string to it") {
        THEN("The same password is a match") {
          EXPECT("password" == password);
        }
        THEN("A different password is a match") {
          EXPECT_NOT("different" == password);
        }
      }
      
    }
  }
};

MODULE(specification(), module);
