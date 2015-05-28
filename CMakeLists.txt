PROJECT(2200 CXX)
CMAKE_MINIMUM_REQUIRED(VERSION 3.2.0 FATAL_ERROR)

#------------------------------------------------------------------------------
# Set up the general configuration
#------------------------------------------------------------------------------
SET(CMAKE_CXX_STANDARD 14)
SET(CMAKE_CXX_STANDARD_REQUIRED ON)
ENABLE_TESTING()

#------------------------------------------------------------------------------
# Set up any library dependencies that we are going to need
#------------------------------------------------------------------------------
FIND_PACKAGE(PkgConfig)

SET(Boost_USE_STATIC_LIBS ON)
SET(Boost_USE_MULTITHREADED ON)
SET(Boost_USE_STATIC_RUNTIME ON)
FIND_PACKAGE(Boost 1.55.0 COMPONENTS system)

FIND_PACKAGE(OpenSSL)
#------------------------------------------------------------------------------
# The main game library
#------------------------------------------------------------------------------
ADD_LIBRARY(2200Lib STATIC
  modules/2200/main/main.cpp
  modules/2200/main/users/password.cpp)
TARGET_INCLUDE_DIRECTORIES(2200Lib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/2200/main)
TARGET_INCLUDE_DIRECTORIES(2200Lib PUBLIC ${OPENSSL_INCLUDE_DIR})
TARGET_LINK_LIBRARIES(2200Lib ${OPENSSL_LIBRARIES})

ADD_EXECUTABLE(2200LibTest
    modules/shared/test/main.cpp
    modules/2200/test/users/passwordTest.cpp)
TARGET_INCLUDE_DIRECTORIES(2200LibTest PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/2200/main)
TARGET_INCLUDE_DIRECTORIES(2200LibTest PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/shared/test)
TARGET_INCLUDE_DIRECTORIES(2200LibTest PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/external/catch/single_include)
TARGET_LINK_LIBRARIES(2200LibTest 2200Lib)
ADD_TEST(NAME 2200Lib COMMAND 2200LibTest)

#------------------------------------------------------------------------------
# The Telnet network server
#------------------------------------------------------------------------------
ADD_LIBRARY(2200NetworkLib STATIC
  modules/network/main/socketAddress.cpp
  modules/network/main/server.cpp)
TARGET_INCLUDE_DIRECTORIES(2200NetworkLib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/network/main)

ADD_EXECUTABLE(2200NetworkTest
    modules/shared/test/main.cpp
    modules/network/test/testAddress.cpp)
TARGET_INCLUDE_DIRECTORIES(2200NetworkTest PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/network/main)
TARGET_INCLUDE_DIRECTORIES(2200NetworkTest PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/shared/test)
TARGET_INCLUDE_DIRECTORIES(2200NetworkTest PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/external/catch/single_include)
TARGET_LINK_LIBRARIES(2200NetworkTest 2200NetworkLib)
ADD_TEST(NAME 2200NetworkLib COMMAND 2200NetworkTest)

#------------------------------------------------------------------------------
# The main game executable
#------------------------------------------------------------------------------
ADD_EXECUTABLE(2200
  modules/2200/main/exec.cpp)
TARGET_INCLUDE_DIRECTORIES(2200 PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/2200/main)
TARGET_LINK_LIBRARIES(2200 2200Lib)
