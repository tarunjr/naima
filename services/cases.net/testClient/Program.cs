using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace testClient
{
    class Program
    {
        static void Main(string[] args)
        {
            // TODO: Replace with your local URI.
            string serviceUri = "http://localhost:2235/odata/";
            var container = new Default.Container(new Uri(serviceUri));
            //ListAllLocations(container);
            //AddPatient(container);
            //AddCareProvider(container);
            //AddDoctor(container);
            AddCase(container);
            System.Console.WriteLine("Dine");
        }
        private static void AddPatient(Default.Container container)
        {
            var patient = new testClient.Org.Naima.Services.Cases.Models.Patient() { 
                Name = "ABD",
                Id = 2,
                LocationId = 2
            };
            container.AddToPatients(patient);
            var serviceResponse = container.SaveChanges();
            foreach (var operationResponse in serviceResponse)
            {
                Console.WriteLine("Response: {0}", operationResponse.StatusCode);
            }

        }
        private static void AddDoctor(Default.Container container)
        {
            var doc1 = new testClient.Org.Naima.Services.Cases.Models.Doctor()
            {
                Name = "Dr Arvind",
                Id = 1,
                LocationId = 1
            };
            var doc2 = new testClient.Org.Naima.Services.Cases.Models.Doctor()
            {
                Name = "Dr Kumar",
                Id = 2,
                LocationId = 2
            };
            container.AddToDoctors(doc2);
            container.AddToDoctors(doc1);
            var serviceResponse = container.SaveChanges();
            foreach (var operationResponse in serviceResponse)
            {
                Console.WriteLine("Response: {0}", operationResponse.StatusCode);
            }

        }
        private static void AddCareProvider(Default.Container container)
        {
            var cp1 = new testClient.Org.Naima.Services.Cases.Models.CareProvider()
            {
                Name = "Ramlal",
                Id = 1,
                LocationId = 1
            };
            var cp2 = new testClient.Org.Naima.Services.Cases.Models.CareProvider()
            {
                Name = "Mohan",
                Id = 2,
                LocationId = 2
            };
            container.AddToCareProviders(cp1);
            container.AddToCareProviders(cp2);
            var serviceResponse = container.SaveChanges();
            foreach (var operationResponse in serviceResponse)
            {
                Console.WriteLine("Response: {0}", operationResponse.StatusCode);
            }

        }
        private static void AddCase(Default.Container container)
        {
            var c1 = new testClient.Org.Naima.Services.Cases.Models.Case()
            {
                Status = "Open",
                Id = 1,
                PatientId = 1,
                DoctorId = 1,
                CareProviderId = 1
            };
            var c2 = new testClient.Org.Naima.Services.Cases.Models.Case()
            {
                Status = "Closed",
                Id = 2,
                PatientId = 1,
                DoctorId = 2,
                CareProviderId = 2
            };
            var c3 = new testClient.Org.Naima.Services.Cases.Models.Case()
            {
                Status = "Open",
                Id = 3,
                PatientId = 2,
                DoctorId = 2,
                CareProviderId = 2
            };
            container.AddToCases(c1);
            container.AddToCases(c2);
            container.AddToCases(c3);
            var serviceResponse = container.SaveChanges();
            foreach (var operationResponse in serviceResponse)
            {
                Console.WriteLine("Response: {0}", operationResponse.StatusCode);
            }

        }
        static void ListAllLocations(Default.Container container)
        {
            foreach (var p in container.Locations)
            {
                Console.WriteLine("{0} {1} {2}", p.District, p.SubDistrict, p.Locality);
            }
        }
    }
}
