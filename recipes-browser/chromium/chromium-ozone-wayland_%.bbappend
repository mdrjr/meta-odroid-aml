FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Add NV12M (multi-plane) to the Linux renderable fourcc list so the V4L2
# stateful decoder works on drivers (Amlogic meson-vdec) that expose only
# NM12 as the CAPTURE format. 
SRC_URI += "file://0001-add-NM12-to-linux-renderable-fourccs.patch"

PACKAGECONFIG ??= "use-egl use-v4l2 use-linux-v4l2 proprietary-codecs"
PACKAGECONFIG[use-linux-v4l2] = "use_v4l2_codec=true use_v4lplugin=true use_linux_v4l2_only=true"

GN_ARGS:append = " fatal_linker_warnings=false"

# Switch to ANGLE, since the newer ozone requires passthrough command decoder.
# See:
# https://issues.chromium.org/issues/40135856
CHROMIUM_EXTRA_ARGS:remove = "--use-gl=egl"
CHROMIUM_EXTRA_ARGS:append = " --use-gl=angle --use-angle=gles-egl --use-cmd-decoder=passthrough"

CHROMIUM_EXTRA_ARGS:append = " --no-sandbox --gpu-sandbox-start-early --ignore-gpu-blacklist --ignore-gpu-blocklist --enable-accelerated-video-decode"

# HACK: VDA and VEA might depend on Vaapi on linux in some old versions.
# CHROMIUM_EXTRA_ARGS:append = " --enable-features=VaapiVideoDecoder,VaapiVideoEncoder"

CHROMIUM_EXTRA_ARGS:append = " --enable-features=AcceleratedVideoEncoder,AcceleratedVideoDecoder,AcceleratedVideoDecodeLinuxGL,AcceleratedVideoDecodeLinuxZeroCopyGL"

CHROMIUM_EXTRA_ARGS:append = " --enable-wayland-ime"
