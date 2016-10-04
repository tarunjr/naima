using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Org.Naima.Services.Cases.Models
{
    public class CasesContext : DbContext
    {
        public CasesContext() 
                : base("name=CasesContext")
        {
        }

        public DbSet<Case> Cases { get; set; }
        public DbSet<Patient> Patients { get; set; }
        public DbSet<Doctor> Doctors { get; set; }
        public DbSet<CareProvider> CareProviders { get; set; }
        public DbSet<Location> Locations { get; set; }

    }
}