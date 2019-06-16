DESCRIPTION = "libglvnd is a vendor-neutral dispatch layer for arbitrating OpenGL API calls between multiple vendors."
HOMEPAGE = "https://github.com/NVIDIA/libglvnd"
LICENSE = "MIT & BSD"
LIC_FILES_CHKSUM = "file://README.md;beginline=309;md5=f98ec0fbe6c0d2fbbd0298b5d9e664d3"

SRC_URI = "git://github.com/NVIDIA/libglvnd \
           file://0001-Build-with-x11-or-wayland-egl.patch \
           file://0002-Add-generation-of-pkgconfig-files.patch \
           "
# tag v1.1.1
SRCREV = "bc9990b7bede44899627354d0c356f48950f4ad1"

COMPATIBLE_MACHINE = "(tegra)"
COMPATIBLE_MACHINE_tegra124 = "(-)"

REQUIRED_DISTRO_FEATURES = "opengl"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGE_ARCH_tegra = "${SOC_FAMILY_PKGARCH}"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
"

PACKAGECONFIG[x11] = "--enable-glx,--disable-glx,libx11 libxext xorgproto"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland"
