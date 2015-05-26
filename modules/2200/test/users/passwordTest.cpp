#include "test.h"

const lest::test module[] = {
    CASE("A passing test" "[pass]") {
        int answer = 42;
        EXPECT( 42 == answer );
    }
};

MODULE(specification(), module);
