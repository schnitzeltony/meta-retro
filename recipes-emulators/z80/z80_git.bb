DESCRIPTION = "Radio Shack® TRS-80 and the EACA Colour Genie EG2000 emulator"
HOMEPAGE = "http://pmbits.de/40846.html"
AUTHOR = "Jürgen Buchmüller"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

SRC_URI = " \
    git://github.com/schnitzeltony/z80.git;branch=master \
    file://0001-use-pkg-config-to-find-sdl.patch \
    file://cgenie.desktop \
    file://trs80.desktop \
"

SRCREV= "413ca44336c4423ac0e39b55d75fce95ff4c31fc"
PV = "0.3.1+git${SRCPV}"

inherit pkgconfig

DEPENDS = "expat libsdl"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "-e MAKEFLAGS="

do_compile() {
	SDL_INC=$(pkg-config --cflags sdl)
	SDL_LIB=$(pkg-config --libs sdl)
	EXPAT_LIB=$(pkg-config --libs expat)

	mkdir -p ${S}/obj/trs80
	mkdir -p ${S}/obj/cgenie

	oe_runmake "CFLAGS=${CFLAGS} -I${STAGING_INCDIR} ${SDL_INC} -I${S}/include" "LD=${CC}" "LDFLAGS=${LDFLAGS} ${SDL_LIB} ${EXPAT_LIB}"
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/bin/* ${D}/${bindir}

	install -d ${D}/${datadir}/cgenie
	install -d ${D}/${datadir}/trs80
	cp -rf ${S}/cgenie/* ${D}/${datadir}/cgenie
	cp -rf ${S}/trs80/* ${D}/${datadir}/trs80

	install -d ${D}/${datadir}/applications
	install -m 0644 ${WORKDIR}/*.desktop ${D}/${datadir}/applications
}

FILES_${PN} += "${datadir}/cgenie ${datadir}/trs80"
