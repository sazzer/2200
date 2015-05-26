#include "lest.hpp"

lest::tests& specification() {
    static lest::tests tests;
    return tests;
}

/**
 * Main runner for the tests
 * @param argc The number of arguments
 * @param argv The actual arguments
 */
int main( int argc, char * argv[] )
{
    return lest::run( specification(), argc, argv );
}
