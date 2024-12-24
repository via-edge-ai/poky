BBCLASSEXTEND = "nativesdk"

require qemu.inc

DEPENDS = "glib-2.0 zlib bison-native ninja-native meson-native"

DEPENDS:append:libc-musl = " libucontext"

CFLAGS += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', '-DEGL_NO_X11=1', d)}"

RDEPENDS:${PN}:class-target += "bash"

EXTRA_OECONF:append:class-target = " --target-list=${@get_qemu_target_list(d)}"
EXTRA_OECONF:append:class-target:mipsarcho32 = "${@bb.utils.contains('BBEXTENDCURR', 'multilib', ' --disable-capstone', '', d)}"
EXTRA_OECONF:append:class-nativesdk = " --target-list=${@get_qemu_target_list(d)}"

PACKAGECONFIG ??= " \
    fdt kvm pie slirp \
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa xen', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'seccomp', d)} \
"
PACKAGECONFIG:class-nativesdk ??= "fdt kvm pie slirp \
"
# ppc32 hosts are no longer supported in qemu
COMPATIBLE_HOST:powerpc = "null"
