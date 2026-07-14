# Pyntar ProGuard Rules
# Add project-specific ProGuard rules here.
# https://www.guardsquare.com/proguard

# Keep Room entity class names for database schema reflection
-keep class com.alfanro.pyntar.core.database.entity.** { *; }

# Keep Hilt-generated component classes
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Kotlin serialization (if added later)
-keepattributes *Annotation*
-keepclassmembers class ** {
    @kotlinx.serialization.Serializable *;
}

# Keep data classes used across module boundaries
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
