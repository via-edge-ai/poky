require shadow.inc

BBCLASSEXTEND = "native nativesdk"

# Severity is low and marked as closed and won't fix.
# https://bugzilla.redhat.com/show_bug.cgi?id=884658
CVE_CHECK_IGNORE += "CVE-2013-4235"

# This is an issue for a different shadow
CVE_CHECK_IGNORE += "CVE-2016-15024"
