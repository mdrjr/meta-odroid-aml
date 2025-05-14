PREFERRED_PROVIDER_virtual/libgl ?= "mesa-gl"
PREFERRED_PROVIDER_virtual/mesa ?= "mesa-gl"

PREFERRED_PROVIDER_virtual/egl ?= "libmali-odroid-c5"
PREFERRED_PROVIDER_virtual/libgles1 ?= "libmali-odroid-c5"
PREFERRED_PROVIDER_virtual/libgles2 ?= "libmali-odroid-c5"
PREFERRED_PROVIDER_virtual/libgles3 ?= "libmali-odroid-c5"
PREFERRED_PROVIDER_virtual/libopencl ?= "libmali-odroid-c5"
PREFERRED_PROVIDER_virtual/libgbm ?= "libmali-odroid-c5"

DEPENDS:append:pn-mesa-gl = " libmali-odroid-c5"
