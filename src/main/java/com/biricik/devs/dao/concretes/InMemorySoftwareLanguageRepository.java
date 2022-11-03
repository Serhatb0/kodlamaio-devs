package com.biricik.devs.dao.concretes;



// @Repository
// public class InMemorySoftwareLanguageRepository implements ProgrammingLanguageRepository {

//     List<SoftwareLanguage> softwareLanguages;

//     public InMemorySoftwareLanguageRepository() {
//         softwareLanguages = new ArrayList<>();

//         softwareLanguages.add(new SoftwareLanguage(1, "Java"));
//         softwareLanguages.add(new SoftwareLanguage(2, "C#"));
//         softwareLanguages.add(new SoftwareLanguage(3, "Python"));
//         softwareLanguages.add(new SoftwareLanguage(4, "Kotlin"));
//         softwareLanguages.add(new SoftwareLanguage(5, "JavaScript"));

//     }

//     @Override
//     public List<SoftwareLanguage> getSoftwareLanguage() {
//         return softwareLanguages;
//     }

//     @Override
//     public SoftwareLanguage getByIdSoftwareLanguage(int id) {
//         Optional<SoftwareLanguage> softwareLanguage = softwareLanguages.stream()
//                 .filter(s -> s.getId() == id).findFirst();
//         return softwareLanguage.get();

//     }

//     @Override
//     public SoftwareLanguage addSoftwareLanguage(SoftwareLanguage softwareLanguage) {
//         boolean isExsist = softwareLanguages.stream().anyMatch(s -> s.getName().equals(softwareLanguage.getName()));
//         if (isExsist) {
//             throw new RuntimeException("İsimler tekrar edemez");
//         }
//         softwareLanguages.add(softwareLanguage);
//         return softwareLanguage;
//     }

//     @Override
//     public SoftwareLanguage updateSoftwareLanguage(SoftwareLanguage softwareLanguage) {
//         Optional<SoftwareLanguage> updateSoftwareLanguage = softwareLanguages.stream()
//                 .filter(item -> item.getId() == softwareLanguage.getId()).findFirst();
//         int index = softwareLanguages.indexOf(updateSoftwareLanguage.get());
//         softwareLanguages.set(index, softwareLanguage);
//         return null;
//     }

//     @Override
//     public void deleteSoftwareLanguage(int id) {
//         Optional<SoftwareLanguage> deleteSoftwareLanguage = softwareLanguages.stream()
//                 .filter(item -> item.getId() == id).findFirst();
//         softwareLanguages.remove(deleteSoftwareLanguage.get());
//     }

// }
