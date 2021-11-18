DESCRIPTION = "Cas juggler"
AUTHOR = "Jürgen Buchmüller"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9fa70e05c591cf3ab4016b22aab701df"

SRC_URI = "git://github.com/pullmoll/cass80.git;branch=master;protocol=https"
SRCREV= "372455830343e5c8ebd42125cfe13191f976c343"
PV = "0.0.0+git${SRCPV}"
S = "${WORKDIR}/git"

inherit qmake5

DEPENDS = "qtbase"

do_install:append() {
    install -d ${D}${bindir}
    mv ${D}/opt/cass80/bin/cass80 ${D}${bindir}
    rm -r ${D}/opt
}


