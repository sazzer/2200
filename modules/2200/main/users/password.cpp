#include "password.h"

namespace Users {
    Password Password::hash(const std::string& password) {
        return Password::hash(password, "salt");
    }

    Password Password::hash(const std::string& password, const std::string& salt) {
        return Password(password, salt);
    }
}
