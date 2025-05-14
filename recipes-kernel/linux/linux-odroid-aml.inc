# Copyright (C) 2025, Hardkernel Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

inherit python3-dir

DEPENDS:append = " openssl-native lz4-native ${PYTHON_PN}-native"

LINUX_VERSION_EXTENSION ?= "-odroidaml-${LINUX_KERNEL_TYPE}"

PATCHPATH = "${THISDIR}/${BPN}_${LINUX_VERSION}"
inherit auto-patch

INSANE_SKIP:${PN}-src += "buildpaths"
