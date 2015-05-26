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

#------------------------------------------------------------------------------
# Set up the actual builds
#------------------------------------------------------------------------------
ADD_LIBRARY(2200Lib STATIC
  modules/2200/main/main.cpp
  modules/2200/main/users/password.cpp)
TARGET_INCLUDE_DIRECTORIES(2200Lib PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/2200/main)

ADD_EXECUTABLE(2200
  modules/2200/main/exec.cpp)
TARGET_INCLUDE_DIRECTORIES(2200 PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/modules/2200/main)
TARGET_LINK_LIBRARIES(2200 2200Lib)