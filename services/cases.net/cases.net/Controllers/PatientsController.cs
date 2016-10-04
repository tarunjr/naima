using Org.Naima.Services.Cases.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.OData;

namespace cases.net.Controllers
{
    public class PatientsController : ODataController
    {
        CasesContext db = new CasesContext();

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }

        [EnableQuery]
        public IQueryable<Patient> Get()
        {
            return db.Patients;
        }
        [EnableQuery]
        public SingleResult<Patient> Get([FromODataUri]int key)
        {
            var result = db.Patients.Where(l => l.Id == key);
            return SingleResult.Create<Patient>(result);
        }
        [EnableQuery]
        public SingleResult<Location> GetLocation([FromODataUri] int key)
        {
            var result = db.Patients.Where(m => m.Id.Equals(key)).Select(m => m.Location);
            return SingleResult.Create<Location>(result);
        }
        [EnableQuery]
        public IQueryable<Case> GetCases([FromODataUri] int key)
        {
            return db.Patients.Where(m => m.Id.Equals(key)).SelectMany(m => m.Cases);
        }
        public async Task<IHttpActionResult> Post(Patient patient)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            db.Patients.Add(patient);
            await db.SaveChangesAsync();
            return Created(patient);
        }
    }
}
