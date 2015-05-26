#include "test.h"
#include "users/password.h"
#include <regex>

static const auto UUID_REGEX = std::regex("^[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}$");

const lest::test module[] = {
    SCENARIO("Hashing a password") {
        GIVEN("A Plaintext password") {
            const std::string plaintext = "password";

            WHEN("Hashed with a random salt") {
                const Users::Password password = Users::Password::hash(plaintext);

                THEN("The salt is a UUID") {
                    EXPECT(std::regex_match(password.salt(), UUID_REGEX));
                }

                THEN("The hash is an SHA256 string") {
                }
            }


        }
    }
};

MODULE(specification(), module);
