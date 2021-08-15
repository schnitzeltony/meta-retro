SUMMARY = "All layer packages - just for build test"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit packagegroup

RDEPENDS:${PN} = " \
    midori \
    \
    dosbox dosbox-staging dosbox-x \
    mame \
    stella \
    vice \
    cass80 z80 \
    \
    doomretro \
    d1x-rebirth \
    \
    cage \
    wlroots \
"

